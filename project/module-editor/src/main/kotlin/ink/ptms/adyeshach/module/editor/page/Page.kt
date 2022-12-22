package ink.ptms.adyeshach.module.editor.page

import ink.ptms.adyeshach.core.entity.TickService
import ink.ptms.adyeshach.module.editor.EditPanel
import ink.ptms.adyeshach.module.editor.RIGHT_ARROW
import ink.ptms.adyeshach.module.editor.clearScreen
import ink.ptms.adyeshach.module.editor.lang
import taboolib.module.chat.TellrawJson

/**
 * Adyeshach
 * ink.ptms.adyeshach.module.editor.page.Page
 *
 * @author 坏黑
 * @since 2022/12/19 18:22
 */
abstract class Page(val editor: EditPanel) {

    val player = editor.player
    val entity = editor.entity

    val json = TellrawJson().newLine()

    var index = 0

    open fun open(index: Int = 0) {
        this.index = index
        // 清屏
        player.clearScreen()
        // 标题
        appendTitle().newLine()
    }

    fun appendTitle(): TellrawJson {
        json.append("  ")
        // 管理器类型
        val manager = entity.manager
        if (manager?.isPublic() == true) {
            json.appendLang("manager-public")
        } else {
            json.appendLang("manager-private")
        }
        // 孤立单位
        if (manager == null || manager !is TickService) {
            json.appendLang("manager-isolated")
        }
        // 临时单位
        else if (manager.isTemporary()) {
            json.appendLang("manager-temporary")
        }
        // 分页
        json.append(" §8§l${RIGHT_ARROW}§r ")
        // 类型
        json.append("§e${entity.entityType.name}")
        // 分页
        json.append(" §8§l${RIGHT_ARROW}§r ")
        // ID
        json.append("§f${entity.id}")
        return json
    }

    fun TellrawJson.appendLang(node: String, vararg args: Any): TellrawJson {
        return append(player.lang(node, *args))
    }
}