package br.com.felipe.spring_boot_essentials.service;

import br.com.felipe.spring_boot_essentials.database.model.AlunosEntity;
import br.com.felipe.spring_boot_essentials.database.model.AvaliacoesFisicasEntity;
import br.com.felipe.spring_boot_essentials.database.model.TreinosEntity;
import br.com.felipe.spring_boot_essentials.database.repository.IAlunosRepository;
import br.com.felipe.spring_boot_essentials.database.repository.IAvaliacoesFisicasRepository;
import br.com.felipe.spring_boot_essentials.database.repository.ITreinosRepository;
import br.com.felipe.spring_boot_essentials.dto.AlunoDto;
import br.com.felipe.spring_boot_essentials.exception.BadRequestException;
import br.com.felipe.spring_boot_essentials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunosService {

    private final IAvaliacoesFisicasRepository avaliacoesFisicasRepository;
    private final ITreinosRepository treinosRepository;
    private final IAlunosRepository alunosRepository;

    public void criarAluno(AlunoDto alunoDto) {
        AlunosEntity aluno = alunosRepository.findByEmail(alunoDto.getEmail())
                .orElse(null);

        if (aluno != null) {
            throw new BadRequestException("Aluno já cadastrado com este email");
        }

        alunosRepository.save(AlunosEntity.builder()
                    .nome(alunoDto.getNome())
                    .email(alunoDto.getEmail())
                    .build());
    }

    public AvaliacoesFisicasEntity getAlunoAvaliacao(Integer alunoId) {
        AlunosEntity aluno = alunosRepository.findByIdFetch(alunoId)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        AvaliacoesFisicasEntity avaliacao = aluno.getAvaliacaoFisica();

        if (avaliacao == null) {
            throw new NotFoundException("Avaliação física não encontrada para este aluno");
        }

        return avaliacao;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletarAluno(Integer alunoId) {
        AlunosEntity aluno = alunosRepository.findByIdFetch(alunoId)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        //1. deletar treinos do aluno
        List<Integer> treinosAlunoIds = aluno.getTreinos().stream()
                .map(TreinosEntity::getId)
                .toList();

        treinosRepository.deleteAllById(treinosAlunoIds);

        //2. deletar aluno
        alunosRepository.deleteById(alunoId);

        //3. deletar avaliação física do aluno
        avaliacoesFisicasRepository.deleteById(aluno.getAvaliacaoFisica().getId());
    }
}
