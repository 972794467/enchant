/*function getPageComponent(){
    return '<nav aria-label="Page navigation">'+
  '<ul class="pagination pagination-lg"> '+
    '<li><a v-on:click="pagePrevious"  aria-label="Previous"><span aria-hidden="true">&laquo;</span> </a> </li> '+
    '<li v-for="page_number in pages" v-bind:class="{active : page_number.is_active}" v-on:click="changePage(page_number.page_index)"><a  v-bind:class="{hidden : page_number.is_hidden}">{{page_number.page_index}}</a></li>'+
    '<li><a v-on:click="pageNext" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>'+
  '</ul></nav>';
}*/
function getPageList(pn,ps,funCallBack,strUrl) {
        var resData = {};
        resData.pn = pn;
        resData.ps = ps;
        var strData = JSON.stringify(resData);
        $.ajax({  type: 'POST',
                         contentType: "application/json;charset=utf-8",
                         url: strUrl, 
                         data: strData,
                         dataType:'json',
                         success: function(data){
                            funCallBack(pn,ps,data);
                         },
                         error: function() {
                             alert('失败');
                         }
          })
    }
function setPagination(intPageNumber,intCurrentPage,intActivePage) {
    pageList.pages = [];
    for(var i=1;i<=intPageNumber;i++){
        if(i <= intCurrentPage+4 && i >= intCurrentPage-4){
            if(intActivePage == i ) {
                pageList.pages.push({page_index:i,is_hidden:false,is_active:true});
                continue;
            }
            pageList.pages.push({page_index:i,is_hidden:false});
        }else{
            pageList.pages.push({page_index:i,is_hidden:true});
        }
    }
    
    
}

function musicListCallBack(pn,ps,data) {
    var arrData = data;
    var intMusicCount = arrData['count'];
    musicList.$data.musics_count = intMusicCount;
    pageList.$data.pages_sum = Math.ceil(intMusicCount/pageList.$data.page_size)
    musicList.musics = [];
    for( intIndex in arrData['music']){
        musicList.musics.push({music_id:arrData['music'][intIndex]['music_id'],music_name:arrData['music'][intIndex]['music_name'],
        artist_name:arrData['music'][intIndex]['artist_name'],music_file:arrData['music'][intIndex]['music_file']});
    }
    setPagination(pageList.$data.pages_sum,pn,pn);
}
function feedBackCallBack(pn,ps,data) {
    var arrData = data;
    var intCount = arrData['count'];
    feedbackList.$data.feedback_count = intCount;
    pageList.$data.pages_sum = Math.ceil(intCount/pageList.$data.page_size)
    feedbackList.feedbacks = [];
    for( intIndex in arrData['feedback']){
        console.log(arrData['feedback'][intIndex]);
        feedbackList.feedbacks.push({id:arrData['feedback'][intIndex]['id'],title:arrData['feedback'][intIndex]['title'],
        content:arrData['feedback'][intIndex]['content'],create_time:arrData['feedback'][intIndex]['create_time']});
    }
    setPagination(pageList.$data.pages_sum,pn,pn);
}
function userListCallBack(pn,ps,data) {
    var arrData = data;
    var intCount = arrData['count'];
    userList.$data.users_count = intCount;
    pageList.$data.pages_sum = Math.ceil(intCount/pageList.$data.page_size)
    userList.users = [];
    for( intIndex in arrData['users']){
        userList.users.push({user_id:arrData['users'][intIndex]['id'],user_name:arrData['users'][intIndex]['name'],
        user_email:arrData['users'][intIndex]['email'],user_sex:arrData['users'][intIndex]['sex'],
        user_type:arrData['users'][intIndex]['type'],user_avatar:arrData['users'][intIndex]['avatar']});
    }
    setPagination(pageList.$data.pages_sum,pn,pn);
}