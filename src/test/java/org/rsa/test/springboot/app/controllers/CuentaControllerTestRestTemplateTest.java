package org.rsa.test.springboot.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.rsa.test.springboot.app.models.TransaccionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CuentaControllerTestRestTemplateTest {

    @Autowired
    private TestRestTemplate client;

    private ObjectMapper objectMapper;

    @LocalServerPort
    private int puerto;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    @Order(1)
    void testTransferir() {
        TransaccionDto dto = new TransaccionDto();
        dto.setBancoId(1L);
        dto.setCuentaOrigenId(1L);
        dto.setCuentaDestinoId(2L);
        dto.setMonto(new BigDecimal("100"));

        ResponseEntity<String> response = this.client
                .postForEntity(crearUri("/api/cuentas/transferir"), dto, String.class);
        String json = response.getBody();
        System.out.println(this.puerto);
        System.out.println(json);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(APPLICATION_JSON, response.getHeaders().getContentType());
        assertNotNull(json);
        assertTrue(json.contains("Transferencia realizada con éxito"));
        assertTrue(json.contains("{\"bancoId\":1,\"cuentaOrigenId\":1,\"cuentaDestinoId\":2,\"monto\":100}"));
    }

    private String crearUri(String uri) {
        return String.format("http://localhost:%d%s", this.puerto, uri);
    }

}