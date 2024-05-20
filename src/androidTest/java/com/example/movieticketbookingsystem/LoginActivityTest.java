import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.test.core.app.ApplicationProvider;

import com.example.movieticketbookingsystem.LoginActivity;
import com.example.movieticketbookingsystem.HomePage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowToast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.P})
public class LoginActivityTest {

    private LoginActivity activity;
    private EditText t1, t2;
    private Button loginButton;

    @Before
    public void setUp() {
        // Initialize the activity
        activity = Robolectric.buildActivity(LoginActivity.class).create().start().resume().get();

        // Retrieve the EditText and Button from the activity
        t1 = activity.findViewById(R.id.uname);
        t2 = activity.findViewById(R.id.password);
        loginButton = activity.findViewById(R.id.loginbutton);
    }

    @Test
    public void testLoginSuccess() {
        // Set the username and password in the intent
        Intent intent = new Intent();
        intent.putExtra("uname", "testUser");
        intent.putExtra("password", "testPass");
        activity.setIntent(intent);

        // Set the EditText fields with the same username and password
        t1.setText("testUser");
        t2.setText("testPass");

        // Perform the button click
        loginButton.performClick();

        // Verify Toast message
        Toast latestToast = ShadowToast.getLatestToast();
        assertNotNull(latestToast);
        assertEquals(Toast.LENGTH_SHORT, latestToast.getDuration());
        assertEquals("LOGIN SUCCESSFUL", ShadowToast.getTextOfLatestToast());

        // Verify if the HomePage activity was started
        Intent expectedIntent = new Intent(activity, HomePage.class);
        Intent actualIntent = shadowOf(activity).getNextStartedActivity();
        assertNotNull(actualIntent);
        assertEquals(expectedIntent.getComponent(), actualIntent.getComponent());
    }

    @Test
    public void testLoginFailure() {
        // Set the username and password in the intent
        Intent intent = new Intent();
        intent.putExtra("uname", "testUser");
        intent.putExtra("password", "testPass");
        activity.setIntent(intent);

        // Set the EditText fields with incorrect username and password
        t1.setText("wrongUser");
        t2.setText("wrongPass");

        // Perform the button click
        loginButton.performClick();

        // Verify Toast message
        Toast latestToast = ShadowToast.getLatestToast();
        assertNotNull(latestToast);
        assertEquals(Toast.LENGTH_SHORT, latestToast.getDuration());
        assertEquals("Login Failed", ShadowToast.getTextOfLatestToast());

        // Verify that the HomePage activity was not started
        Intent actualIntent = shadowOf(activity).getNextStartedActivity();
        assertEquals(null, actualIntent);
    }
}
