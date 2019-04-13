package bme.aut.nikoletn.advices

import android.app.Activity
import androidx.fragment.app.Fragment

val Activity.injector: AdvicesApplicationComponent
    get() {
        return (this.applicationContext as AdvicesApplication).injector
    }

val Fragment.injector: AdvicesApplicationComponent
    get() {
        return (this.context!!.applicationContext as AdvicesApplication).injector
    }