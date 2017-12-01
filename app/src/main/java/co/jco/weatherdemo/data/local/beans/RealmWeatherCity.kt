package co.jco.weatherdemo.data.local.beans

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * This class needs to be open since Realm will extend it to override getters/setters
 */
open class RealmWeatherCity : RealmObject() {

    @PrimaryKey var example: String = ""


}
