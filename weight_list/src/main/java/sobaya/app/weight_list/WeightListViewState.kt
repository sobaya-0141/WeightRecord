package sobaya.app.weight_list

import sobaya.app.database.entity.Weight

internal data class WeightListViewState(
    val dailyWeightData: List<Weight>?
) {
    companion object {
        val EMPTY = WeightListViewState(null)
    }
}