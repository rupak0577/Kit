package core.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.parameters.arguments.argument
import org.example.core.*
import org.example.util.writeDefaultConfig
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.createDirectories
import kotlin.io.path.createDirectory
import kotlin.io.path.exists
import kotlin.io.path.isDirectory

class Init : CliktCommand(name = "init") {
    private val directory by argument()

    override fun help(context: Context) = "Initialize the repo"
    override fun run() {
        val path = Paths.get(directory).toAbsolutePath()
        val gitDir = Paths.get(directory).resolve(DIR_KIT)

        var repo: KitRepository? = null

        if (path.exists()) {
            if (path.isDirectory().not()) {
                throw Exception("Not a directory!")
            }

            if (path.resolve(DIR_KIT).exists() &&
                Files.list(path).findAny().isPresent
            )
                throw Exception("Directory not empty!")
        } else {
            gitDir.createDirectories()
            repo = KitRepository(gitDir, true)
        }

        if (repo == null)
            throw Exception("Could not create repository!")

        gitDir.resolve(DIR_BRANCHES).createDirectory()
        gitDir.resolve(DIR_OBJECTS).createDirectory()
        gitDir.resolve(DIR_REFS).resolve(DIR_TAGS).createDirectories()
        gitDir.resolve(DIR_REFS).resolve(DIR_HEADS).createDirectories()

        gitDir.resolve(FILE_DESCRIPTION).toFile().printWriter().use { out ->
            out.println("Unnamed repository; edit this file 'description' to name the repository.\n")
        }

        gitDir.resolve(FILE_HEAD).toFile().printWriter().use { out ->
            out.println("ref: refs/heads/master\n")
        }

        gitDir.resolve(FILE_CONFIG).toFile().printWriter().use { out ->
            writeDefaultConfig(repo.config)
        }
    }
}