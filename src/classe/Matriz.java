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
    
    public Matriz multiplicar(Matriz objMatriz) throws Exception{
        if(this.quantidadeDeLinhas != objMatriz.quantidadeDeLinhas || this.quantidadeDeColunas != objMatriz.quantidadeDeColunas){
            throw new Exception("As matrizes não são de mesma ordem!");
        }
        
        Matriz aux = new Matriz(this.quantidadeDeLinhas, this.quantidadeDeColunas);
        
        int soma = 0;
        for(int l = 0; l < this.quantidadeDeLinhas; l++){
            for(int c = 0; c < objMatriz.quantidadeDeColunas; c++){
                soma = 0;
                
                for(int i = 0; i < this.quantidadeDeColunas; i++){
                    soma += this.objMatriz[l][i] * objMatriz.objMatriz[i][c];
                    aux.objMatriz[l][c] = soma;
                }
            }
        }
        return aux;
    }
    
    public Matriz calcularTransposta(Matriz objMatriz) throws Exception{
        
        Matriz aux = new Matriz(this.quantidadeDeColunas, this.quantidadeDeLinhas);
        for (int c = 0; c < this.quantidadeDeColunas; c++) {
            for (int l = 0; l < this.quantidadeDeLinhas; l++) {
                aux.objMatriz[c][l] = this.objMatriz[c][l];
            }
        }
        
        return aux;
    }
    
    public Matriz calcularPotencia(int expoente) throws Exception{
        if(expoente < 0){
            throw new Exception ("O expoente não pode ser negativo.");
        }
        
        Matriz aux = new Matriz(quantidadeDeLinhas, quantidadeDeColunas);
        
        if(expoente == 0 ){
            for (int l = 0; l < quantidadeDeLinhas; l++) {
                for (int c = 0; c < quantidadeDeColunas; c++) {
                    if(l == c) aux.objMatriz[l][c] = 1;
                    else aux.objMatriz[l][c] = 0;
                }
            }
            return aux;
        }else if(expoente == 1){
            return this;
        }else{
            for(int l = 0; l < quantidadeDeLinhas; l++) {
                for (int c = 0; c < quantidadeDeColunas; c++) {
                    aux.objMatriz[l][c] = this.objMatriz[l][c];
                }
            }
            for (int i = 0; i < expoente-1; i++) {
                aux = aux.multiplicar(this);
            }
            return aux;
        }
    }
    
    public boolean eTriangularSuperior() throws Exception{
        if(quantidadeDeColunas != quantidadeDeLinhas){
            return false;
        }else{
            for(int l = 0; l < objMatriz.length; l++){
                for(int c = 0; c < objMatriz.length; c++) {
                    if(l > c && objMatriz[l][c] != 0){
                        return false;
                    }
                }
            }
            return true;
        }
    }
    
    public boolean eTriangularInferior() throws Exception{
        if(quantidadeDeColunas != quantidadeDeLinhas){
            return false;
        }else{
            for(int l = 0; l < objMatriz.length; l++){
                for(int c = 0; c < objMatriz.length; c++){
                    if(l < c && objMatriz[l][c] != 0){
                        return false;
                    }
                }
            }
            return true;
        }
    }
    
    public boolean eSimetrica() throws Exception{
        if(quantidadeDeColunas != quantidadeDeLinhas){
            return false;
        }else{ 
            for(int l = 0; l < objMatriz.length; l++) {
                for(int c = 0; c < objMatriz.length; c++) {
                    if(objMatriz[l][c] != objMatriz[c][l]){
                        return false;
                    }
                }
            }
            return true;
        }
    }
    
    public boolean eIdentidade() throws Exception{
        if(quantidadeDeColunas != quantidadeDeLinhas){
            return false;
        }else{
            for(int l = 0; l < quantidadeDeLinhas; l++){
                for(int c = 0; c < quantidadeDeColunas; c++){
                    if(l == c && objMatriz[l][c] != 1){
                        return false;
                    }else if(l != c && objMatriz[l][c] != 0){
                        return false;
                    }
                }
            }
            return true;
        }
    }
    
    public boolean eOrtogonal() throws Exception{
        if(this.quantidadeDeLinhas != this.quantidadeDeColunas){
            return false;
        }
        //return this.multiplicar(this.calcularTransposta(objMatriz)).eIdentidade();
        return true;
    }
    
    public boolean ePermutacao() throws Exception{
        if(this.quantidadeDeLinhas != this.quantidadeDeColunas){
            return false;
        }
        
        int soma = 0;
        int soma2 = 0;
        
        for(int l = 0; l < quantidadeDeLinhas; l++){
            soma = 0;
            soma2 = 0;
            
            for(int c = 0; c < quantidadeDeColunas; c++) {
                if(this.objMatriz[l][c] != 0 && this.objMatriz[l][c] != 1){
                    return false;
                }
                
                soma += this.objMatriz[l][c];
                soma2 += this.objMatriz[c][l];
                
                if(soma > 1){
                    return false;
                }
                
                if(soma2 > 1){
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean eIgual(Matriz objMatriz) throws Exception{
        
        for(int l = 0; l < this.quantidadeDeLinhas; l++){
            for(int c = 0; c < this.quantidadeDeColunas; c++){
                if(this.objMatriz[l][c] != objMatriz.objMatriz[l][c]){
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean eDiferente(Matriz objMatriz) throws Exception{
        
        for(int l = 0; l < this.quantidadeDeLinhas; l++){
            for(int c = 0; c < this.quantidadeDeColunas; c++){
                if(this.objMatriz[l][c] != objMatriz.objMatriz[l][c]){
                    return true;
                }
            }
        }
        return false;
    }
}
