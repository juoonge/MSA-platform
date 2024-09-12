    @Builder
    public Vendor(String name, VendorType vendorType, String address, UUID vendorManagerUserId, UUID belongingHubId) {
        this.name = name;
        this.vendorType = vendorType;
        this.address = address;
        this.vendorManagerUserId = vendorManagerUserId;
        this.belongingHubId = belongingHubId;
    }

    public void changeBelongingHub(UUID belongingHubId) {
        this.belongingHubId = belongingHubId;
    }
