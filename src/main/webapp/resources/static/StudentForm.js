$(document).ready(function () {

    const sel_fac = $("#sel_fac");
    const sel_cat = $("#sel_cat");
    const sel_grp = $("#sel_grp");

    let fac_id = sel_fac.children("option:selected").val();
    let cat_id = sel_cat.children("option:selected").val();
    let grp_id = sel_grp.children("option:selected").val();

    let load_fac = false;
    let load_cat = false;
    let load_grp = false;

    logId();

    sel_fac.click(function () {
        loadClick(load_fac, sel_fac, "faculties", "/Students-WebApp/faculties/getFacList", fac_id);
    });

    sel_fac.change(function () {
        whenItChanges(sel_fac, "faculties");
    });

    sel_cat.click(function () {
        loadClick(load_cat, sel_cat, "cathedras", "/Students-WebApp/cathedras/getCatList?facultyId=" + fac_id, cat_id);
    });

    sel_cat.change(function () {
        whenItChanges(sel_cat, "cathedras");
    });

    sel_grp.click(function () {
        loadClick(load_grp, sel_grp, "groups", "/Students-WebApp/group_classes/getGroupList?cathedraId=" + cat_id, grp_id);
    })

    function logId() {
        console.log("fac_id = " + fac_id + "; cat_id = " + cat_id + "; grp_id = " + grp_id);
    }

    function loadClick(load, selector, entity, url, id) {
        if (load) {
            console.log(entity + " already load");
        } else {
            selector.children().remove();
            $.getJSON(url, function (response) {
                $.each(response, function (index) {
                    let option = $("<option>");
                    console.log(response[index].id + "  " + response[index].title);
                    option.val(response[index].id);
                    option.text(response[index].title);
                    selector.append(option);
                });
                if (id !== "") {
                    let sel_id;
                    switch (entity) {
                        case 'faculties':
                            sel_id = "sel_fac";
                            break;
                        case 'cathedras':
                            sel_id = "sel_cat";
                            break;
                        case 'groups':
                            sel_id = "sel_grp";
                            break;
                    }
                    $("#" + sel_id + " option[value=" + id + "]").attr('selected', 'selected');
                }
                id = selector.children("option:selected").val();
                switch (entity) {
                    case 'faculties':
                        fac_id = id;
                        load_fac = true;
                        break;
                    case 'cathedras':
                        cat_id = id;
                        load_cat = true;
                        break;
                    case 'groups':
                        grp_id = id;
                        load_grp = true;
                        break;
                }
                console.log("successfully load " + entity + ", selected Id = " + id);
                logId();
            });
        }
    }

    function whenItChanges(selector, entity) {
        switch (entity) {
            case 'faculties':
                fac_id = selector.children("option:selected").val();
                sel_cat.children().remove();
                sel_grp.children().remove();
                cat_id = null;
                grp_id = null;
                load_cat = false;
                load_grp = false;
                break;
            case 'cathedras':
                cat_id = selector.children("option:selected").val();
                sel_grp.children().remove();
                grp_id = null;
                load_grp = false;
                break;
        }
        logId();
    }
});
