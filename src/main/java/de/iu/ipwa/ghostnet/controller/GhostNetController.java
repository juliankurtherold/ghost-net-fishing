package de.iu.ipwa.ghostnet.controller;

import de.iu.ipwa.ghostnet.model.GhostNet;
import de.iu.ipwa.ghostnet.model.GhostNetStatus;
import de.iu.ipwa.ghostnet.model.BergendePerson;
import de.iu.ipwa.ghostnet.service.GhostNetService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.annotation.PostConstruct;
import java.util.List;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
public class GhostNetController {

    private GhostNet ghostNet = new GhostNet();

    private BergendePerson bergendePerson = new BergendePerson();

    private Long selectedGhostNetId;

    private GhostNetService service = new GhostNetService();

    private List<GhostNet> ghostNets;

    private boolean showOnlyOpenGhostNets;

    @PostConstruct
    public void init() {
        loadGhostNets();
    }

    public void save() {
        service.saveGhostNet(ghostNet);
        ghostNet = new GhostNet(); // Formular zurücksetzen
        loadGhostNets();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Geisternetz wurde erfolgreich gespeichert."));
    }

    public void updateGhostNet(GhostNet ghostNet) {
        service.updateGhostNet(ghostNet);
        loadGhostNets();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Status wurde erfolgreich aktualisiert."));
    }

    public void markAsRecovered(GhostNet ghostNet) {
        ghostNet.setStatus(GhostNetStatus.GEBORGEN);
        service.updateGhostNet(ghostNet);
        loadGhostNets();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Geisternetz wurde als geborgen gemeldet."));
    }

    public void registerForRecovery() {
        if (selectedGhostNetId == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Hinweis", "Bitte zuerst ein Geisternetz auswählen."));
            return;
        }

        GhostNet selectedGhostNet = service.findGhostNetById(selectedGhostNetId);

        if (selectedGhostNet == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Hinweis", "Das ausgewählte Geisternetz wurde nicht gefunden."));
            return;
        }

        service.saveBergendePerson(bergendePerson);
        service.assignBergendePerson(selectedGhostNet, bergendePerson);

        bergendePerson = new BergendePerson();
        selectedGhostNetId = null;

        loadGhostNets();

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Bergende Person wurde erfolgreich eingetragen."));
    }

    public void deleteGhostNet(GhostNet ghostNet) {
        service.deleteGhostNet(ghostNet);
        loadGhostNets();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Geisternetz wurde erfolgreich gelöscht."));
    }

    public GhostNet getGhostNet() {
        return ghostNet;
    }

    public BergendePerson getBergendePerson() {
        return bergendePerson;
    }

    public Long getSelectedGhostNetId() {
        return selectedGhostNetId;
    }

    public void setSelectedGhostNetId(Long selectedGhostNetId) {
        this.selectedGhostNetId = selectedGhostNetId;
    }

    public GhostNetStatus[] getStatuses() {
        return GhostNetStatus.values();
    }

    public List<GhostNet> getGhostNets() {
        return ghostNets;
    }

    public boolean isShowOnlyOpenGhostNets() {
        return showOnlyOpenGhostNets;
    }

    public void setShowOnlyOpenGhostNets(boolean showOnlyOpenGhostNets) {
        this.showOnlyOpenGhostNets = showOnlyOpenGhostNets;
        loadGhostNets();
    }

    private void loadGhostNets() {
        if (showOnlyOpenGhostNets) {
            ghostNets = service.findOpenGhostNets();
        } else {
            ghostNets = service.findAll();
        }
    }
}
