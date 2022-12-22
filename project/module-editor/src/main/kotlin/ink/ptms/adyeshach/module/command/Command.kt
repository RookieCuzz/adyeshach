package ink.ptms.adyeshach.module.command

import ink.ptms.adyeshach.core.Adyeshach
import ink.ptms.adyeshach.core.entity.manager.ManagerType
import ink.ptms.adyeshach.module.command.subcommand.createSubCommand
import ink.ptms.adyeshach.module.command.subcommand.editSubCommand
import ink.ptms.adyeshach.module.command.subcommand.removeSubCommand
import ink.ptms.adyeshach.module.command.subcommand.renameSubCommand
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.common.util.unsafeLazy
import taboolib.expansion.createHelper

/**
 * Adyeshach
 * ink.ptms.adyeshach.module.command.Command
 *
 * @author 坏黑
 * @since 2022/12/17 20:47
 */
@CommandHeader(name = "adyeshach", aliases = ["ady", "npc"], permission = "adyeshach.command")
object Command {

    val finder by unsafeLazy { Adyeshach.api().getEntityFinder() }
    val manager by unsafeLazy { Adyeshach.api().getPublicEntityManager(ManagerType.PERSISTENT) }

    @CommandBody
    val main = mainCommand {
        createHelper()
    }

    @CommandBody
    val script = CommandScript

    @CommandBody
    val create = createSubCommand

    @CommandBody
    val remove = removeSubCommand

    @CommandBody
    val rename = renameSubCommand

    @CommandBody
    val edit = editSubCommand
}