package com.framgia.oleo.utils

import androidx.annotation.IntDef


@IntDef(Index.POSITION_ZERO, Index.POSITION_ONE)
annotation class Index {
    companion object {
        const val POSITION_ZERO = 0
        const val POSITION_ONE = 1
    }
}
