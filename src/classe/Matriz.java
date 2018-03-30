package classe;

/**
 *
 * @author Franzwagner Ternus
 */
public class Matriz {
    // Atributos
    private int quantidadeDeLinhas = 0;
    private int quantidadeDeColunas = 0;
    private int[][] objMatriz = null;

    // Métodos
    public Matriz(int qLinhas, int qColunas) throws Exception{
      if(qLinhas <= 0 || qColunas <= 0){
        throw new Exception("Quantidade de Linhas ou Colunas não pode ser menor ou igual a 0!");
      }

      objMatriz = new int[qLinhas][qColunas];
      this.quantidadeDeLinhas = qLinhas;
      this.quantidadeDeColunas = qColunas;
    }
    
    public int getQuantidadeDeLinhas(){
        return quantidadeDeLinhas;
    }
    
    public int getQuantidadeDeColunas(){
        return quantidadeDeColunas;
    }
    
    public int getElemento(int linha, int coluna) throws Exception{
        if(linha < 0 || linha >= quantidadeDeLinhas){
            throw new Exception("Linha fora do intervalo válido!");
        }
        
        if(coluna < 0 || coluna >= quantidadeDeColunas){
            throw new Exception("Coluna fora do intervalo válido!");
        }
        
        return objMatriz[linha][coluna];
    }
    
    public void setElemento(int linha, int coluna, int elemento) throws Exception{
        if(linha < 0 || linha >= quantidadeDeLinhas){
            throw new Exception("Linha fora do intervalo válido!");
        }
        
        if(coluna < 0 || coluna >= quantidadeDeColunas){
            throw new Exception("Coluna fora do intervalo válido!");
        }
        
        objMatriz[linha][coluna] = elemento;
    }
    
    public String getMatriz(){
        String saida = "";
        
        for(int l = 0; l < quantidadeDeLinhas; l++){
            for(int c = 0; c < quantidadeDeColunas; c++){
                saida += objMatriz[l][c] + " ";
            }
            saida += "\n";
        }
        
        return saida;
    }
    
    public Matriz adicionar(Matriz objMatriz) throws Exception{
        if(this.quantidadeDeLinhas != objMatriz.quantidadeDeLinhas || this.quantidadeDeColunas != objMatriz.quantidadeDeColunas){
            throw new Exception("As matrizes não são de mesma ordem!");
        }
        
        Matriz aux = new Matriz(this.quantidadeDeLinhas, this.quantidadeDeColunas);
        
        for(int l = 0; l < this.quantidadeDeLinhas; l++){
            for(int c = 0; c < this.quantidadeDeColunas; c++){
                int valor = this.getElemento(l, c) + objMatriz.getElemento(l, c);
                aux.setElemento(l, c, valor);
            }
        }
        
        return aux;
    }
    
    public Matriz subtrair(Matriz objMatriz) throws Exception{
        if(this.quantidadeDeLinhas != objMatriz.quantidadeDeLinhas || this.quantidadeDeColunas != objMatriz.quantidadeDeColunas){
            throw new Exception("As matrizes não são de mesma ordem!");
        }
        
        Matriz aux = new Matriz(this.quantidadeDeLinhas, this.quantidadeDeColunas);
        
        for(int l = 0; l < this.quantidadeDeLinhas; l++){
            for(int c = 0; c < this.quantidadeDeColunas; c++){
                int valor = this.getElemento(l, c) - objMatriz.getElemento(l, c);
                aux.setElemento(l, c, valor);
            }
        }
        
        return aux;
    }
}
