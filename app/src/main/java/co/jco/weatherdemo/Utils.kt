package co.jco.weatherdemo

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Helper function to replace a fragment in a transation
 */
fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.beginTransaction().replace(frameId, fragment).commit()
}