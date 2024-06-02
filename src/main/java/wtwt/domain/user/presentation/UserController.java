package wtwt.domain.user.presentation;

import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import wtwt.common.doc.swagger.UserSwagger;
import wtwt.common.dto.response.IdResponse;
import wtwt.common.dto.response.ScrollResponse;
import wtwt.domain.auth.application.dto.response.UserSummary;
import wtwt.domain.user.application.UserService;
import wtwt.domain.user.presentation.dto.request.CheckEmailDuplicateApiReq;
import wtwt.domain.user.presentation.dto.request.CheckNicknameDuplicateApiReq;
import wtwt.domain.user.presentation.dto.request.SignUpApiReq;
import wtwt.domain.user.presentation.dto.request.UpdateBirthdateApiReq;
import wtwt.domain.user.presentation.dto.request.UpdateGenderApiReq;
import wtwt.domain.user.presentation.dto.request.UpdateProfileApiReq;
import wtwt.domain.user.presentation.dto.request.UpdateUserApiReq;
import wtwt.domain.user.presentation.dto.response.CheckDuplicateApiRes;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController implements UserSwagger {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<IdResponse> signup(@RequestBody @Valid SignUpApiReq request) {
        Long id = userService.signUp(request.toSignUpReq());
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/users/{id}")
            .buildAndExpand(id)
            .toUri();

        return ResponseEntity.created(uri)
            .body(IdResponse.from(id));
    }

    @PostMapping("/email/check")
    public ResponseEntity<CheckDuplicateApiRes> checkEmailDuplicate(
        @RequestBody @Valid CheckEmailDuplicateApiReq request) {
        CheckDuplicateApiRes response = CheckDuplicateApiRes.builder()
            .isDuplicated(userService.isDuplicatedEmail(request.email()))
            .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/nickname/check")
    public ResponseEntity<CheckDuplicateApiRes> checkNicknameDuplicate(
        @RequestBody @Valid CheckNicknameDuplicateApiReq request) {
        CheckDuplicateApiRes response = CheckDuplicateApiRes.builder()
            .isDuplicated(userService.isDuplicatedNickname(request.nickname()))
            .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IdResponse> updateUser(
        @PathVariable Long id,
        @RequestBody @Valid UpdateUserApiReq request
    ) {
        Long userId = userService.updateUser(id, request.toUpdateUserReq());
        return ResponseEntity.ok().body(IdResponse.from(userId));
    }

    @GetMapping
    public ResponseEntity<ScrollResponse<UserSummary>> search(UserSearch request) {
        ScrollResponse<UserSummary> response = userService.search(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/profile")
    @Deprecated
    public ResponseEntity<Void> updateProfile(@PathVariable Long id,
        @RequestBody @Valid UpdateProfileApiReq request) {
        return null;
    }

    @PutMapping("/{id}/birthdate")
    @Deprecated
    public ResponseEntity<Void> updateBirthdate(@PathVariable Long id,
        @RequestBody @Valid UpdateBirthdateApiReq request) {
        return null;
    }

    @PutMapping("/{id}/gender")
    @Deprecated
    public ResponseEntity<Void> updateGender(@PathVariable Long id,
        @RequestBody @Valid UpdateGenderApiReq request) {
        return null;
    }
}
