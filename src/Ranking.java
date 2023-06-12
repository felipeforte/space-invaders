import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Ranking {
    ArrayList lista = new ArrayList<Posicao>();
    String nomeArquivo = "tabela.txt";

    public Ranking() {
        povoarLista();
    }

    public Posicao get(int i) {
        return (Posicao) lista.get(i);
    }

    public String getPosicaoNome(int i) {
        if (isVazia()) {
            return "";
        } else {
            String retorno = "";
            if (i<lista.size()) {
                retorno = get(i).getNome();
            }
            return retorno;
        }
    }

    public String getPosicaoScore(int i) {
        if (isVazia()) {
            return "";
        } else {
            String retorno = "";
            if (i<lista.size()) {
                retorno = get(i).retornaPontos();
            }
            return retorno;
        }
    }

    public boolean isVazia() {
        return lista.isEmpty();
    }

    public void povoarLista() {
        if (checaArquivo(retornaArquivo())) {
            String[] linhas = leArquivo().split("\n");
            for (int i=0;i<linhas.length;i++) {
                Posicao p = new Posicao(linhas[i].split(",")[0],Integer.parseInt(linhas[i].split(",")[1]));
                adiciona(p);
            }
            ordena();
        }
    }

    public boolean checaArquivo(File arquivo) {
        if (arquivo.exists()) {
            try {
                FileReader fileReader = new FileReader(arquivo);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                if (bufferedReader.readLine() == null) {
                    return false;
                }
                if (bufferedReader.equals("")) {
                    return false;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    public File retornaArquivo() {
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return arquivo;
    }

    public String leArquivo() {
        File arquivo = retornaArquivo();
        try {
            FileReader fileReader = new FileReader(arquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String retorno = "";
            String linha = bufferedReader.readLine();

            for (int i=0;linha != null;i++) {
                if (i==0) {
                    retorno += linha;
                    linha = bufferedReader.readLine();
                } else {
                    retorno += "\n" + linha;
                    linha = bufferedReader.readLine();
                }
            }

            while (linha != null) {
                retorno += linha;
                linha = bufferedReader.readLine();
            }
            return retorno;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void escreveArquivo(Posicao p) {
        File arquivo = retornaArquivo();
        try {
            FileWriter fw = new FileWriter(arquivo, true);

            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(p.getNome() + "," + p.getPontos() + "\n");
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void adiciona(Posicao p) {
        lista.add(p);
    }

    public void ordena() {
        Collections.sort(lista);
    }
}
class Posicao implements Comparable<Posicao> {
    private String nome;
    private int pontos;

    public Posicao(String nome, int pontos) {
        this.nome = nome;
        this.pontos = pontos;
    }

    public String getNome() {
        return nome;
    }

    public int getPontos() {
        return pontos;
    }

    public String retornaPontos() {
        return Integer.toString(pontos);
    }

    @Override
    public int compareTo(Posicao p) {
        return p.getPontos()-getPontos();
    }
}