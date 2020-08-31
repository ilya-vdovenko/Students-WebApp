$(document).ready(function () {
    let fac_id = $("#sel_fac").children("option:selected").val();
    let cat_id = $("#sel_cat").children("option:selected").val();
    let grp_id = $("#sel_grp").children("option:selected").val();

    let load_fac = false;
    let load_cat = false;
    let load_grp = false;

    console.log("fac_id = " + fac_id + "; cat_id = " + cat_id + "; grp_id = " + grp_id);

    $("#sel_fac").click(function () {
        if (load_fac) {
            console.log("faculties already load");
        } else {
            $("#sel_fac").children().remove();
            $.getJSON("/faculties/getFacList", function (response) {
                $.each(response, function (index) {
                    let option = $("<option>");
                    console.log(response[index].id + "  " + response[index].title);
                    option.val(response[index].id);
                    option.text(response[index].title);
                    $("#sel_fac").append(option);
                });
                if (fac_id !== "") $("#sel_fac option[value=" + fac_id + "]").attr('selected', 'selected');
                load_fac = true;
                fac_id = $("#sel_fac").children("option:selected").val();
                console.log("successfully load faculties, selected facId = " + $("#sel_fac").children("option:selected").val());
                log();
            });
        }
    });

    $("#sel_fac").change(function () {
        fac_id = $(this).children("option:selected").val();
        $("#sel_cat").children().remove();
        $("#sel_grp").children().remove();
        cat_id = null;
        grp_id = null;
        load_cat = false;
        load_grp = false;
        log();
    });

    $("#sel_cat").click(function () {
        if (load_cat) {
            console.log("cathedras already load");
        } else {
            $("#sel_cat").children().remove();
            $.getJSON("/cathedras/getCatList?facultyId=" + fac_id, function (response) {
                $.each(response, function (index) {
                    let option = $("<option>");
                    console.log(response[index].id + "  " + response[index].title);
                    option.val(response[index].id);
                    option.text(response[index].title);
                    $("#sel_cat").append(option);
                });
                if (cat_id !== "") $("#sel_cat option[value=" + cat_id + "]").attr('selected', 'selected');
                load_cat = true;
                cat_id = $("#sel_cat").children("option:selected").val();
                console.log("successfully load cathedras, selected catId = " + cat_id);
                log();
            });
        }
    });

    $("#sel_cat").change(function () {
        cat_id = $(this).children("option:selected").val();
        $("#sel_grp").children().remove();
        grp_id = null;
        load_grp = false;
        log();
    });

    $("#sel_grp").click(function () {
        if (load_grp) {
            console.log("groups already load");
        } else {
            $("#sel_grp").children().remove();
            $.getJSON("/group_classes/getGroupList?cathedraId=" + cat_id, function (response) {
                $.each(response, function (index) {
                    let option = $("<option>");
                    console.log(response[index].id + "  " + response[index].number);
                    option.val(response[index].id);
                    option.text(response[index].number);
                    $("#sel_grp").append(option);
                });
                if (grp_id !== "") $("#sel_grp option[value=" + grp_id + "]").attr('selected', 'selected');
                load_grp = true;
                grp_id = $("#sel_grp").children("option:selected").val();
                console.log("successfully load groups, selected grpId = " + grp_id);
                log();
            });
        }
    });

    function log() {
        console.log("fac_id = " + fac_id + "; cat_id = " + cat_id + "; grp_id = " + grp_id);
    }
});
