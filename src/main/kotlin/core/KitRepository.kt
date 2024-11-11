package org.example.core

import org.example.util.getConfigFile
import org.example.util.getOption
import org.ini4j.Wini
import java.nio.file.Path
import kotlin.io.path.isDirectory

class KitRepository(workTree: Path, force: Boolean) {
    val config: Wini = Wini(getConfigFile(workTree, FILE_CONFIG))

    init {
        if (!force && workTree.isDirectory())
            throw Exception("Not a valid repository")

        if (!force) {
            val repoVer = config.getOption<Int>("core", "repositoryformatversion")
            if (repoVer != 0)
                throw Exception("Unsupported repositoryformatversion $repoVer")
        }
    }
}