package cloudcomputing2024.smarthouse.trafficmonitorservice.alerts;

public record TrafficExceededAlert(String service, TrafficExceededCause cause) {
}
