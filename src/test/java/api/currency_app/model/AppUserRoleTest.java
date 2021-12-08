package api.currency_app.model;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppUserRoleTest {
    private AppUser appUser;

    @Before
    public void setUp() {
        appUser = new AppUser();
    }

    @Test
    void testGetRole() {
        Role.RoleName user = Role.RoleName.USER;
        AppUser appUser = new AppUser();
        appUser.setAppUserRole(user.USER);
        assertEquals(user, appUser.getAppUserRole());
    }
}
