package cloudcomputing2024.smarthouse.trafficmonitorservice.presentation.boundaries;

public class ExternalReferenceBoundary {
    private String service;
    private String externalServiceId;

    ExternalReferenceBoundary(){}

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getExternalServiceId() {
        return externalServiceId;
    }

    public void setExternalServiceId(String externalServiceId) {
        this.externalServiceId = externalServiceId;
    }

    @Override
    public String toString() {
        return "ExternalReferenceBoundary{" +
                "service='" + service + '\'' +
                ", externalServiceId='" + externalServiceId + '\'' +
                '}';
    }
}
