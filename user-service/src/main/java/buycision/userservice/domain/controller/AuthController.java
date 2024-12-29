package buycision.userservice.domain.controller;

import buycision.userservice.domain.dto.request.LoginRequest;
import buycision.userservice.domain.dto.response.LoginResponse;
import buycision.userservice.domain.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login/{oAuthType}")
    public ResponseEntity<LoginResponse> login(
            @RequestBody @Valid LoginRequest request,
            @PathVariable String oAuthType,
            @RequestHeader Map<String, String> headers
    ) {
        return ResponseEntity.ok(authService.login(request, oAuthType, getDeviceType(headers)));
    }

    private String getDeviceType(Map<String, String> headers) {
        return headers.entrySet().stream()
                .filter(entry -> entry.getKey().equals("x-device-type"))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }
}
