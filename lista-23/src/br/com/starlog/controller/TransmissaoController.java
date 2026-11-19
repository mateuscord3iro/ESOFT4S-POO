package br.com.starlog.controller;

import br.com.starlog.model.CriptoMensagem;
import java.util.ArrayList;
import java.util.List;

/** Recebe os dados da interface, aciona o Model e registra os pacotes. */
public class TransmissaoController {
    private final List<String> bufferTransmissoes = new ArrayList<>();

    public String processarTransmissao(String remetenteRaw, String msgRaw, String chaveStr) {
        final int deslocamento;
        try {
            deslocamento = Integer.parseInt(chaveStr.trim());
        } catch (NumberFormatException | NullPointerException ex) {
            throw new IllegalArgumentException("Chave deve ser um número inteiro válido.", ex);
        }

        CriptoMensagem cripto = new CriptoMensagem(remetenteRaw, msgRaw, deslocamento);
        String textoCifrado = cripto.cifrar();
        int checksum = cripto.calcularChecksum();
        String pacote = String.format("[%s] #%d -> %s", cripto.getRemetente(), checksum, textoCifrado);

        bufferTransmissoes.add(pacote);
        return pacote;
    }

    public int getTotalTransmissoes() {
        return bufferTransmissoes.size();
    }
}
