package com.ziven0069.miniproject2.navigation

import com.ziven0069.miniproject2.screen.KEY_ID_BUKU

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object FormBaru: Screen("detailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_BUKU}"){
        fun withId(id: Long) = "detailScreen/$id"
    }
}