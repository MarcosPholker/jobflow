package com.jobflow.auth_service.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobflow.auth_service.dto.CompanyDTO;
import com.jobflow.auth_service.dto.UsuarioDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:jobflow_auth;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
    "spring.datasource.username=sa",
    "spring.datasource.password=",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
    "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect"
})
class AuthFlowIntegrationTest {

    @LocalServerPort
    private int port;

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void userRegisterAndLoginShouldWork() throws Exception {
        UsuarioDTO usuarioDTO = new UsuarioDTO("joao", "joao@email.com", "senha123");

        var registerResponse = sendPost("/userregister", usuarioDTO);
        assertThat(registerResponse.statusCode()).isEqualTo(201);

        var loginResponse = sendPost("/userlogin", usuarioDTO);
        assertThat(loginResponse.statusCode()).isEqualTo(200);
        assertThat(loginResponse.body()).isNotBlank();
    }

    @Test
    void companyRegisterAndLoginShouldWork() throws Exception {
        CompanyDTO companyDTO = new CompanyDTO("Empresa JobFlow", "empresa@email.com", "senha123");

        var registerResponse = sendPost("/companyregister", companyDTO);
        assertThat(registerResponse.statusCode()).isEqualTo(201);

        var loginResponse = sendPost("/companylogin", companyDTO);
        assertThat(loginResponse.statusCode()).isEqualTo(200);
        assertThat(loginResponse.body()).isNotBlank();
    }

    private HttpResponse<String> sendPost(String path, Object payload) throws Exception {
        String json = objectMapper.writeValueAsString(payload);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port + path))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
