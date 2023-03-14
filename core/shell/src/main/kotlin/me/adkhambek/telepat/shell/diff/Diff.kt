package me.adkhambek.telepat.shell.diff


public inline fun <Model : Any> diff(block: DiffBuilder<Model>.() -> Unit): ViewRenderer<Model> {
    val builder = object : DiffBuilder<Model>(), ViewRenderer<Model> {
        override fun render(model: Model) {
            binders.forEach { it.render(model) }
        }
    }

    builder.block()
    return builder
}

public open class DiffBuilder<Model : Any> {

    @PublishedApi
    internal val binders: MutableList<ViewRenderer<Model>> = ArrayList()

    public inline fun <T> diff(
        crossinline get: (Model) -> T,
        crossinline compare: (new: T, old: T) -> Boolean = { a, b -> a == b },
        crossinline set: (T) -> Unit,
    ) {
        binders += object : ViewRenderer<Model> {
            private var oldValue: T? = null

            override fun render(model: Model) {
                val newValue = get(model)
                val oldValue = oldValue
                this.oldValue = newValue

                if ((oldValue == null) || !compare(newValue, oldValue)) {
                    set(newValue)
                }
            }
        }
    }
}