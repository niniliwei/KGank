package com.niniliwei.kgank.api

import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

val ioThread get() = Schedulers.io()
val mainThread get() = AndroidSchedulers.mainThread()