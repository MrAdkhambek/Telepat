package me.adkhambek.telepat.shell.exception

import me.adkhambek.pack.text.Text

public class TextException(
    public val text: Text,
) : Exception(text.toString())