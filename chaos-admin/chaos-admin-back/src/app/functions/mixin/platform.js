import Data from '@/chaos/functions/common/Data';

export const platform = {
    data() {
        return {
        }
    },
    methods: {
        platformChange(platformMu) {
            this.data.platformMu = platformMu
            this.search()
        },
        updateFormPlatformChange(platformMu) {
            this.updateForm.platformMu = platformMu
        },
        async showPlatformUpdate(pickRowMu) {
            this.pickRowMu = pickRowMu
            if (this.updateForm.platformMu) {
                await this.initUpdateForm()
            }
            this.showUpdateForm = true
        },
        async platformInit() {
            this.initUpdateForm()
        },
        async initUpdateForm() {
            this.updateForm = await Data.one(this.table, this.pickRowMu)
        }
    }
}















