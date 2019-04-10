package bme.aut.nikoletn.advices

import android.app.Activity

val Activity.injector: AdvicesApplicationComponent
    get() {
        return (this.applicationContext as AdvicesApplication).injector
    }