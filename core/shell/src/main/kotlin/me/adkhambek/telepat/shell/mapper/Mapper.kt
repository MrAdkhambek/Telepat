package me.adkhambek.telepat.shell.mapper

fun interface Mapper<I, O> : (I) -> O {
    override fun invoke(value: I): O
}
