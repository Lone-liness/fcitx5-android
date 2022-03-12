package me.rocka.fcitx5test.ui.common

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.rocka.fcitx5test.R
import splitties.dimensions.dp
import splitties.resources.resolveThemeAttribute
import splitties.resources.styledDimenPxSize
import splitties.resources.styledDrawable
import splitties.views.dsl.core.*
import splitties.views.gravityCenterVertical
import splitties.views.textAppearance

class SimpleAdapter(
    private vararg val items: Pair<String, (() -> Unit)?>
) : RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {

    inner class ViewHolder(val rootView: View, val textView: TextView) :
        RecyclerView.ViewHolder(rootView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        with(parent.context) {
            val textView = textView {
                textAppearance = resolveThemeAttribute(R.attr.textAppearanceListItem)
            }
            ViewHolder(
                horizontalLayout {
                    layoutParams = ViewGroup.LayoutParams(matchParent, wrapContent)
                    setPadding(
                        styledDimenPxSize(android.R.attr.listPreferredItemPaddingLeft), dp(16),
                        styledDimenPxSize(android.R.attr.listPreferredItemPaddingRight), dp(16)
                    )
                    minimumHeight = styledDimenPxSize(android.R.attr.listPreferredItemHeightSmall)
                    gravity = gravityCenterVertical
                    add(textView, lParams {})
                },
                textView
            )
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (string, action) = items[position]
        holder.apply {
            textView.text = string
            with(rootView) {
                if (action != null) {
                    background = styledDrawable(android.R.attr.selectableItemBackground)
                    setOnClickListener { action() }
                } else
                    background = styledDrawable(android.R.attr.itemBackground)
            }

        }
    }

    override fun getItemCount(): Int = items.size

}