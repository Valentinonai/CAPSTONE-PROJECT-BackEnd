package CapstoneProject.CapstoneProject.build;

import CapstoneProject.CapstoneProject.Enum.Stato;
import CapstoneProject.CapstoneProject.exception.NotFoundException;
import CapstoneProject.CapstoneProject.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildService {

    @Autowired
    private BuildRepository buildRepository;

    public Page<Build> getAllBuilds(int page, int size, String order) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));
        return buildRepository.findAll(pageable);
    }

    public Build getSingleBuild(long id) {
        return buildRepository.findById(id).orElseThrow(() -> new NotFoundException("Elemento non trovato"));
    }

    public Build saveBuild(BuildSavePayload body, User u) {
        Build b = new Build(body.items(), u);

        return buildRepository.save(b);
    }

    public Build modifyBuild(BuildSavePayload body, long id) {
        Build b = getSingleBuild(id);
        b.setItems(body.items());

        return buildRepository.save(b);
    }

    public void deleteBuild(long id) {
        Build build = getSingleBuild(id);
        build.setStato(Stato.INATTIVO);
        buildRepository.save(build);
//        buildRepository.delete(build);
    }
    public void setBuildInattiva(List<Build> builds){
        for(int i=0;i<builds.size();i++){
            Build b=getSingleBuild(builds.get(i).getId());
            b.setStato(Stato.INATTIVO);
            buildRepository.save(b);

        }
    }
}
