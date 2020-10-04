package commitware.ayia.onboardingwalkthrough.model

class Slide {

    var heading: String? = null
    var subHeading: String? = null
    var image: Int = 0
    var buttonText: String? = null

    constructor(heading: String, subHeading: String, image: Int, buttonText: String) {
        this.heading = heading
        this.subHeading = subHeading
        this.image = image
        this.buttonText = buttonText
    }

    constructor(heading: String, subHeading: String, image: Int) {
        this.heading = heading
        this.subHeading = subHeading
        this.image = image
    }
}
