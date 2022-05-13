package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {
    public static ProdutoPojo criarProdutoComumComUmValorIgualA(double Valor) {

        ProdutoPojo produto = new ProdutoPojo();
        produto.setProdutoValor(Valor);
        produto.setProdutoNome("Play Station 5");
        produto.setProdutoUrlMock("");

        List<String> cores = new ArrayList<>();
        cores.add("Preto");
        cores.add("Branco");
        produto.setProdutoCores(cores);

        List<ComponentePojo> componentes = new ArrayList();

        ComponentePojo componente = new ComponentePojo();
        componente.setComponenteNome("controle");
        componente.setComponenteQuantidade(1);
        componentes.add(componente);

        ComponentePojo segundoComponente = new ComponentePojo();
        segundoComponente.setComponenteNome("Headset sem fio");
        segundoComponente.setComponenteQuantidade(2);
        componentes.add(segundoComponente);

        produto.setComponentes(componentes);

        return produto;
    }
}
