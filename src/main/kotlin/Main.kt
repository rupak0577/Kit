package org.example

import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands
import core.commands.Init
import core.commands.Kit

fun main(args: Array<String>) = Kit()
    .subcommands(Init())
    .main(args)