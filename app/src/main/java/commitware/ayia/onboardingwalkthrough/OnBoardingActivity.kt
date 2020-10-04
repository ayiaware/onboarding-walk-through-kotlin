package commitware.ayia.onboardingwalkthrough

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import commitware.ayia.onboardingwalkthrough.adapter.SliderAdapter
import commitware.ayia.onboardingwalkthrough.interfaces.OnSliderInteractionListener
import commitware.ayia.onboardingwalkthrough.model.Slide
import java.util.ArrayList

class OnBoardingActivity : AppCompatActivity(), OnSliderInteractionListener{

    private lateinit var sViewPager: ViewPager

    private var dotsLayout: LinearLayout? = null

    private lateinit var sliderAdapter: SliderAdapter


    var slideCount: Int = 0

    private var btnNext: Button? = null

    private var btnPrevious: Button? = null

    var button = arrayOf("setup", "user", "sign up")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_onbording)

        sViewPager = findViewById(R.id.sViewPager)
        dotsLayout = findViewById(R.id.layoutDots)
        btnPrevious = findViewById(R.id.btnCancel)
        btnNext = findViewById(R.id.btnNext)


        val slideList = ArrayList<Slide>()

        slideList.add(
            Slide(
                "Setup Device",
                resources.getString(R.string.dummy_text), R.drawable.mobile, button[0]
            )
        )

        slideList.add(
            Slide(
                "Create new User",
                resources.getString(R.string.dummy_text), R.drawable.mobile, button[1]
            )
        )

        slideList.add(
            Slide(
                "Sign up",
                resources.getString(R.string.dummy_text), R.drawable.mobile, button[2]
            )
        )

        sliderAdapter = SliderAdapter(this, slideList)

        slideCount = sliderAdapter.count

        // set adapter in ViewPager
        sViewPager.adapter = sliderAdapter

        // set PageChangeListener
        sViewPager.addOnPageChangeListener(viewPagerPageChangeListener)

        addBottomDots(0)

        btnPrevious?.setOnClickListener { btnPreviousClick() }

        btnNext?.setOnClickListener { btnNextClick() }

    }

    private fun getItem(i: Int): Int {
        return sViewPager.currentItem + i
    }

    //btnNextClick
    private fun btnNextClick() {
        // checking for last page
        // if last page home screen will be launched
        val current = getItem(1)

        when {
            current < sliderAdapter.count -> // move to next screen
                sViewPager.currentItem = current
            current == sliderAdapter.count -> {
                val intent = Intent(this@OnBoardingActivity, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out)
            }
            else -> onNavigateUp()
        }
    }

    //btnPreviousClick
    private fun btnPreviousClick() {

        // checking for first page

        // if first page finish activity

        val current = getItem(1)

        if (current < sliderAdapter.count) {
            // move to previous screen
            sViewPager.currentItem = current - 1
        } else if (current == 0) {
            finish()
        }

    }


    // viewPagerPage ChangeListener according to Dots-Points
    private var viewPagerPageChangeListener: ViewPager.OnPageChangeListener =
        object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {

                addBottomDots(position)

                if (position == slideCount - 1) {

                    btnNext?.text = resources.getString(R.string.button_text_finish)

                } else {

                    btnNext?.text = resources.getString(R.string.button_text_next)

                }

                if (position > 0) {

                    btnPrevious?.text = resources.getString(R.string.button_text_previous)
                } else if (position == 0) {
                    btnPrevious?.text = resources.getString(R.string.button_cancel)
                }


            }

            override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {

            }

            override fun onPageScrollStateChanged(arg0: Int) {

            }

        }


    // set of Dots points
    private fun addBottomDots(currentPage: Int) {
        val dots = arrayOfNulls<TextView>(slideCount)
        dotsLayout?.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]?.text = Html.fromHtml("&#8226;")
            dots[i]?.textSize = 35f
            dots[i]?.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))  // dot_inactive
            dotsLayout?.addView(dots[i])
        }

        if (dots.isNotEmpty())
            dots[currentPage]?.setTextColor(ContextCompat.getColor(this, R.color.colorAccent)) // dot_active
    }

    override fun buttonClick() {

        Toast.makeText(this, "button clicked " + button[getItem(1) - 1], Toast.LENGTH_SHORT).show()


    }
}
