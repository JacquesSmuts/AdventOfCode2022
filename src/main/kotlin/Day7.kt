/**
 * https://adventofcode.com/2022/day/7
 */
fun day7Task1(input: String): Long {

    return sumOfAllSizesBelow100k(createDirFromCommandList(input))
}

/**
 * https://adventofcode.com/2022/day/7#part2
 */
fun day7Task2(input: String): Long {

    val root = createDirFromCommandList(input)
    val unusedSpaceNeeded = 30000000L - (70000000L - root.size)
    return root.listOfSizes.sorted().first { it > unusedSpaceNeeded }
}

fun createDirFromCommandList(input: String): Directory {

    val root = Directory("root")
    var currentDir = root

    // Build the directory structure
    input.splitByLine().forEach { command ->

        when {
            command.startsWith("$ ls") -> return@forEach
            command.contains("$ cd ") -> {
                currentDir = when (val folderName = command.substringAfter("\$ cd ")){
                    "/" -> root
                    ".." -> currentDir.parent!!
                    else -> currentDir.directories.first { it.name == folderName }
                }
            }
            command.startsWith("dir") -> { // dir abc
                val dirName = command.substringAfter("dir ")
                currentDir.directories.add(Directory(dirName, parent = currentDir))
            }
            else -> { // 244243 a.bat
                val (size, _) = command.split(" ")
                // we never actually use the name, because we assume the inputs are perfect
                currentDir.files.add(File(size.toLong()))
            }
        }
    }
    return root
}

/**
 * This is technically incorrect because it sums a folder as well as its subfolders together. But the question says
 * this is the correct way of summing so 🤷
 */
fun sumOfAllSizesBelow100k(directory: Directory): Long {

    val size = if (directory.size > 100000) 0 else directory.size
    return size + directory.directories.sumOf {
        sumOfAllSizesBelow100k(it)
    }
}

class Directory(
    val name: String,
    val directories: MutableList<Directory> = mutableListOf(),
    val files: MutableList<File> = mutableListOf(),
    val parent: Directory? = null,
) {

    val size: Long get() = directories.sumOf { it.size } + files.sumOf { it.size }

    val listOfSizes: List<Long> get()= directories.map { it.size } + directories.flatMap { it.listOfSizes }
}

class File(
    val size: Long,
)

