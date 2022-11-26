package br.com.alinykelly.orgs.ui.recyclerView.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import br.com.alinykelly.orgs.R
import br.com.alinykelly.orgs.databinding.ProdutoItemBinding
import br.com.alinykelly.orgs.extensions.formatarParaMoedaBrasileira
import br.com.alinykelly.orgs.extensions.tentarCarregarImagem
import br.com.alinykelly.orgs.model.Produto

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto> = emptyList(),
    // declaração da função para o listener do adapter
    var quandoClicarNoItem: (produto: Produto) -> Unit = {},
    var quandoClicarEmEditar: (produto: Produto) -> Unit = {},
    var quandoClicarEmRemover: (produto: Produto) -> Unit = {},
    ) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    // utilização do inner na classe interna para acessar membros da classe superior
    // nesse caso, a utilização da variável quandoClicaNoItem
    inner class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root), PopupMenu.OnMenuItemClickListener {
        // Considerando que o ViewHolder modifica de valor com base na posição
        // é necessário o uso de properties mutáveis, para evitar nullables
        // utilizamos o lateinit, properties que podem ser inicializar depois

        private lateinit var produto: Produto

        init {
            // implementação do listener do adapter
            itemView.setOnClickListener {
                // verificação da existência de valores em property lateinit
                if (::produto.isInitialized){
                    quandoClicarNoItem(produto)
                }
            }
            itemView.setOnLongClickListener {
                PopupMenu(context, itemView).apply {
                    menuInflater.inflate(
                        R.menu.menu_detalhes_produto,
                        menu
                    )
                    setOnMenuItemClickListener(this@ViewHolder)
                }.show()
                true
            }
        }

        //Apresentar produto
        fun vincula(produto: Produto) {
            this.produto = produto

            val nome = binding.produtoItemNome
            nome.text = produto.nome

            val descricao = binding.produtoItemDescricao
            descricao.text = produto.descricao

            val valor = binding.produtoItemValor
            //Formatar valor monetario
            val valorEmMoeda = produto.valor.formatarParaMoedaBrasileira()
            valor.text = valorEmMoeda

            //Verificar imagem
            val visibilidade = if(produto.imagem != null){
                View.VISIBLE
            } else {
                View.GONE
            }
            binding.imageView.visibility = visibilidade

            //Outra maneira de tratar o erro para exibição de imagem
            binding.imageView.tentarCarregarImagem(produto.imagem)
        }

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            item?.let {
                when (it.itemId){
                    R.id.menu_detalhes_produto_editar -> {
                        quandoClicarEmEditar(produto)
                    }
                    R.id.menu_detalhes_produto_remover -> {
                        quandoClicarEmRemover(produto)
                    }
                }
            }
            return true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    override fun getItemCount(): Int = produtos.size

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}
