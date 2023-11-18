package CapstoneProject.CapstoneProject.build;

import CapstoneProject.CapstoneProject.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public Build saveBuild(BuildSavePayload body) {
        Build b = new Build(body.items(), body.user());
        buildRepository.save(b);
        return b;
    }

    public Build modifyBuild(BuildSavePayload body, long id) {
        Build b = getSingleBuild(id);
        b.setItems(body.items());
        buildRepository.save(b);
        return b;
    }

    public void deleteBuild(Build b) {
        Build build = getSingleBuild(b.getId());
        buildRepository.delete(build);
    }
}
