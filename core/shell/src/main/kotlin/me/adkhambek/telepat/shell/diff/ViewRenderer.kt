package me.adkhambek.telepat.shell.diff

import androidx.annotation.MainThread

public interface ViewRenderer<in Model : Any> {

    @MainThread
    public fun render(model: Model)
}