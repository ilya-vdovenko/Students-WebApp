/*
 * Copyright 2019-2020, Ilya Vdovenko and the Students-WebApp contributors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

$(document).ready(function () {

  const selFac = $('#selFac')
  const selCat = $('#selCat')
  const selGrp = $('#selGrp')

  let facId = selFac.children('option:selected').val()
  let catId = selCat.children('option:selected').val()
  let grpId = selGrp.children('option:selected').val()

  let loadFac = false
  let loadCat = false
  let loadGrp = false

  logId()

  selFac.mouseenter(function () {
    loadOnEnter(loadFac, selFac, 'faculties', '/faculties/getFacList', facId)
  })

  selFac.change(function () {
    whenItChanges(selFac, 'faculties')
  })

  selCat.mouseenter(function () {
    loadOnEnter(loadCat, selCat, 'cathedras', '/cathedras/getCatList?facultyId=' + facId, catId)
  })

  selCat.change(function () {
    whenItChanges(selCat, 'cathedras')
  })

  selGrp.mouseenter(function () {
    loadOnEnter(loadGrp, selGrp, 'groups', '/groupClasses/getGroupList?cathedraId=' + catId, grpId)
  })

  function logId () {
    console.log('facId = ' + facId + ' catId = ' + catId + ' grpId = ' + grpId)
  }

  function loadOnEnter (load, selector, entity, url, id) {
    if (load) {
      console.log(entity + ' already load')
    } else {
      selector.children().remove()
      $.getJSON(url, function (response) {
        $.each(response, function (index) {
          const option = $('<option>')
          console.log(response[index].id + '  ' + response[index].title)
          option.val(response[index].id)
          option.text(response[index].title)
          selector.append(option)
        })
        if (id !== '') {
          let selId
          switch (entity) {
            case 'faculties':
              selId = 'selFac'
              break
            case 'cathedras':
              selId = 'selCat'
              break
            case 'groups':
              selId = 'selGrp'
              break
          }
          $('#' + selId + ' option[value=' + id + ']').attr('selected', 'selected')
        }
        id = selector.children('option:selected').val()
        switch (entity) {
          case 'faculties':
            facId = id
            loadFac = true
            break
          case 'cathedras':
            catId = id
            loadCat = true
            break
          case 'groups':
            grpId = id
            loadGrp = true
            break
        }
        console.log('successfully load ' + entity + ', selected Id = ' + id)
        logId()
      })
    }
  }

  function whenItChanges (selector, entity) {
    switch (entity) {
      case 'faculties':
        facId = selector.children('option:selected').val()
        selCat.children().remove()
        selGrp.children().remove()
        catId = null
        grpId = null
        loadCat = false
        loadGrp = false
        break
      case 'cathedras':
        catId = selector.children('option:selected').val()
        selGrp.children().remove()
        grpId = null
        loadGrp = false
        break
    }
    logId()
  }

})
