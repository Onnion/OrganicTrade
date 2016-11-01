package mpoo.bsi.ufrpe.organictrade.OrganicTradeControler.Dominio;

public class ItensDeTenda {

    private String intensdetenda_id;
    private String valor;
    private String quantidadeAtual;
    private String nomeProduto;
    private String usurio_id;

    public String getUsurio_id() {
        return usurio_id;
    }

    public void setUsurio_id(String usurio_id) {
        this.usurio_id = usurio_id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getIntensdetenda_id() {
        return intensdetenda_id;
    }

    public void setIntensdetenda_id(String intensdetenda_id) {
        this.intensdetenda_id = intensdetenda_id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(String quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }


}
