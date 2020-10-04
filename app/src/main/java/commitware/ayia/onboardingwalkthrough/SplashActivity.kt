package commitware.ayia.onboardingwalkthrough

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val it = Intent(this@SplashActivity, OnBoardingActivity::class.java)
            startActivity(it)


            overridePendingTransition(R.anim.fade_in, R.anim.fade_out) // no animation

            this@SplashActivity.finish()
        }, 2000)

    }
}
