package com.greedy.blinddogdate

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CommonUtils
fun <R : Any> R.logger(): Lazy<Logger> = lazy {
    LoggerFactory.getLogger(this.javaClass.name.removeSuffix("\$Companion"))
}
