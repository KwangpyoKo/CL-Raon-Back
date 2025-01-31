package coLaon.ClaonBack.domain;

import coLaon.ClaonBack.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        this.user = User.of(
                "abcd",
                "hoonki",
                "hoonki",
                "asdf",
                "asdf",
                "sdd",
                "sdf",
                "dfdf"
        );
    }

    @Test
    @DisplayName("success modify user")
    void successModifyUser() {
        // when
        this.user.modifyUser(
                "newnickname",
                "metro",
                "basic",
                "imagepath",
                "dfdf",
                "dsfsf"
        );

        // then
        assertThat(this.user.getNickname()).isEqualTo("newnickname");
        assertThat(this.user.getMetropolitanActiveArea()).isEqualTo("metro");
        assertThat(this.user.getBasicLocalActiveArea()).isEqualTo("basic");
        assertThat(this.user.getImagePath()).isEqualTo("imagepath");
        assertThat(this.user.getInstagramOAuthId()).isEqualTo("dsfsf");
        assertThat(this.user.getInstagramUserName()).isEqualTo("dfdf");
    }

    @Test
    @DisplayName("check iscompletedSignUp User")
    void checkIsCompletedSignUp() {
        // given # not completed user
        User user = User.createNewUser("cbh1203@naver.com", "2344");

        // when
        Boolean isCompleted = user.isSignupCompleted();

        // then
        assertThat(isCompleted).isFalse();

        // given # completed user
        User user2 = User.of(
                "test@gmail.com",
                "1234567890",
                "test",
                "경기도",
                "성남시",
                "",
                "",
                "instagramId"
        );

        // when
        isCompleted = user2.isSignupCompleted();

        // then
        assertThat(isCompleted).isTrue();
    }
}
