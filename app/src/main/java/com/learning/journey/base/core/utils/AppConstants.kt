package com.learning.journey.base.core.utils

import com.learning.journey.BuildConfig

object AppConstants {

    var baseUrl = ""
    var s3BaseUrl = ""
    var oAuthClientId = ""

    init {
        if (BuildConfig.BUILD_TYPE == "release" || BuildConfig.BUILD_TYPE == "releaseHuawei") {
            baseUrl = "https://api.edukite.info/"
            s3BaseUrl = "https://asset.edukite.info/"
            oAuthClientId =
                "614399452342-0vramrnsdb0vc6a4i9ue0p5il1fekt2e.apps.googleusercontent.com"
        } else if (BuildConfig.BUILD_TYPE == "uat" || BuildConfig.BUILD_TYPE == "uatHuawei") {
            baseUrl = "https://api.edukite-uat.info/"
            s3BaseUrl = "https://asset.edukite-uat.info/"
            oAuthClientId =
                "614399452342-lkbde2nu3v5av5lonqn5selc5m2b6b70.apps.googleusercontent.com"
        } else {
            baseUrl = "https://api.edukite-dev.info/"
            s3BaseUrl = "https://asset.edukite-dev.info/"
            oAuthClientId =
                "614399452342-pji94l6a8tpdo8sgno6pqkucvlgo65tc.apps.googleusercontent.com"
        }
    }

    var configUrl = s3BaseUrl + "json/edukite_client_global_config.json"

    const val tncRegistration =
        "https://edukite.co.za/terms-conditions?utm_source=app&utm_medium=android&utm_campaign=tnc&utm_content=registration"
    const val tncProfile =
        "https://edukite.co.za/terms-conditions?utm_source=app&utm_medium=android&utm_campaign=tnc&utm_content=profile-menu"
    const val privacyUrlRegistration =
        "https://edukite.co.za/edukite-privacy-policy?utm_source=app&utm_medium=android&utm_campaign=pp&utm_content=registration"
    const val privacyUrlProfile =
        "https://edukite.co.za/edukite-privacy-policy?utm_source=app&utm_medium=android&utm_campaign=pp&utm_content=profile-menu"
    const val promoCodeUrl =
        "https://edukite.co.za/how-codes-work?utm_source=app&utm_medium=android&utm_campaign=codes&utm_content=Registration"
    const val supportEmailId = "support@edukite.co.za"
}
