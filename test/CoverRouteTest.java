import org.junit.*;
import play.libs.F.*;
import play.libs.ws.*;
import play.mvc.*;
import play.test.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static play.test.Helpers.*;

public class CoverRouteTest extends WithApplication {

    @Test
    public void testIsRedirectRoute() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/cover/12");
        Result result = route(request);
        assertThat(result.status(), equalTo(TEMPORARY_REDIRECT));
    }
}
