package br.com.alinykelly.orgs.extensions
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

// função de extensão para reutilizar o código de formatação em diferentes pontos do código
fun BigDecimal.formatarParaMoedaBrasileira(): String {
    val formatador: NumberFormat = NumberFormat
        .getCurrencyInstance(Locale("pt", "br"))
    return formatador.format(this)
}