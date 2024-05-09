package wtwt.domain.auth.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wtwt.common.doc.swagger.AuthSwagger;
import wtwt.domain.auth.application.AuthService;
import wtwt.domain.auth.presentation.dto.request.LoginApiReq;
import wtwt.domain.auth.presentation.dto.request.ReissueTokenApiReq;
import wtwt.domain.auth.presentation.dto.response.LoginApiRes;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController implements AuthSwagger {

    private final AuthService authService;

    @PostMapping("/login/basic")
    public ResponseEntity<LoginApiRes> login(
        @RequestBody @Valid LoginApiReq request
    ) {
        LoginApiRes response = LoginApiRes.from(authService.basicLogin(request.toLoginReq()));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/reissue")
    public ResponseEntity<LoginApiRes> refresh(
        @RequestBody @Valid ReissueTokenApiReq request
    ) {
        LoginApiRes response = LoginApiRes.from(authService.reissue(request.toReissueTokenReq()));

        return ResponseEntity.ok(response);
    }
}
