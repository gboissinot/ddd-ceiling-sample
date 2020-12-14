package fr.gboissinot.telematic.ceiling.sample.exposition;

import fr.gboissinot.kernel.java.annotation.Pattern;

import javax.validation.constraints.NotNull;

@Pattern.DTORequest
@SuppressWarnings({"WeakerAccess", "squid:ClassVariableVisibilityCheck"})
public class ModifyUserGaugeRequest {

    @NotNull
    public long userId;

    @NotNull
    public double amount;
}
