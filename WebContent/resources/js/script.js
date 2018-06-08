function verificar(xhr, status, args, dlg, tab) {
        if(args.validationFailed || !args.loggedIn) {
            PF(dlg).jq.effect("shake", {times:5}, 100);
        }
        else {
            PF(dlg).hide();
            PF(tab).clearFilters();
            
        }
    }